import json
from jsonpath import jsonpath

employees = {'employees': [{'id': 1, 'name': 'Smith', 'salary': '10000'},
                           {'id': 2, 'name': 'Jack', 'salary': '15000'}]}

print('type(employees) return: ')
print(type(employees), employees)

# test json.dumps
json_str = json.dumps(employees)
print('json.dumps(employees) return: ')
print(type(json_str), json_str)

# test json.loads
print('json.loads(json_str) return: ')
json_dict = json.loads(json_str)
print(type(json_dict), json_dict)

print('json.loads("1") return: ')
_int = json.loads("1")
print(type(_int), _int)

print('json.loads("1.1") return: ')
_float = json.loads("1.1")
print(type(_float), _float)

# r只读 w可写 a追加
# test json.dump
f = open('./test.txt', 'w')
print('type(f) return: ')
print(type(f), f)
json.dump(json_dict, f)

# test json.load
f = open('./test.txt', 'r')
test_content = json.load(f)
print('test_content return: ')
print(type(test_content), test_content)

# test jsonpath
# 从根节点开始，匹配id
json_path_content = jsonpath(test_content, '$..id')
print('json_path_content return: ')
print(type(json_path_content), json_path_content)
