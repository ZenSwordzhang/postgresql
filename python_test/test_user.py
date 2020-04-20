import json
import random
import string
import unittest

import requests


class UserControllerTest(unittest.TestCase):

    def test_get_book_by_id(self):
        headers = {'Content-type': 'application/json', 'access-token': ''}
        res_book = requests.get('http://localhost:8088/api/v1/book/1', headers=headers)
        self.assertTrue(res_book.status_code == requests.codes.ok)
        book_id1 = 1
        res_book1 = requests.get(f'http://localhost:8088/api/v1/book/{book_id1}', headers=headers)
        self.assertTrue(res_book1.status_code == requests.codes.ok)
        book_id2 = "2"
        res_book2 = requests.get(f'http://localhost:8088/api/v1/book/{int(book_id2)}', headers=headers)
        self.assertTrue(res_book2.status_code == requests.codes.ok)
        print(res_book2.content)

    def test_get_book_by_author_name(self):
        headers = {'Content-type': 'application/json', 'access-token': ''}
        res_book = requests.get('http://localhost:8088/api/v1/book/author/Bruce', headers=headers)
        self.assertTrue(res_book.status_code == requests.codes.ok)
        author_name = 'Bruce'
        res_book1 = requests.get(f'http://localhost:8088/api/v1/book/author/{author_name}', headers=headers)
        self.assertTrue(res_book1.status_code == requests.codes.ok)
        # 生成4位随机字符串
        author_name2 = ''.join(random.sample(string.ascii_letters + string.digits, 4))
        res_book2 = requests.get(f'http://localhost:8088/api/v1/book/author/{author_name2}', headers=headers)
        self.assertTrue(res_book2.status_code == requests.codes.ok)
        print(res_book2.content)

    def test_get_book_by_name(self):
        headers = {'Content-type': 'application/json', 'access-token': ''}
        params = {'name': 'java'}
        res_book = requests.get('http://localhost:8088/api/v1/book', headers=headers, params=params)
        self.assertTrue(res_book.status_code == requests.codes.ok)
        print(res_book.content)

    def test_update_book(self):
        headers = {'Content-type': 'application/json', 'access-token': ''}
        data = {'id': 1, 'name': 'java'}
        res_book = requests.post('http://localhost:8088/api/v1/book', headers=headers, data=json.dumps(data))
        self.assertTrue(res_book.status_code == requests.codes.ok)
        # Content-type不同
        headers = {'Content-type': 'application/x-www-form-urlencoded'}
        res_book1 = requests.post('http://127.0.0.1:8088/api/v1/book', headers=headers, data=data)
        print(res_book1.content)

    def test_update_author(self):
        headers = {'Content-type': 'application/json', 'access-token': ''}
        params = {'id': 1, 'firstName': 'Bruce'}
        res_book = requests.post('http://localhost:8088/api/v1/author', headers=headers, params=params)
        self.assertTrue(res_book.status_code == requests.codes.ok)
        print(res_book.content)


if __name__ == '__main__':
    unittest.main()
