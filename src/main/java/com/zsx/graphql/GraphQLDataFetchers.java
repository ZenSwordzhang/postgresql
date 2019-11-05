//package com.zsx.graphql;
//
//import com.google.common.collect.ImmutableMap;
//import graphql.schema.DataFetcher;
//import org.springframework.stereotype.Component;
//
//import java.util.Arrays;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class GraphQLDataFetchers {
//
//    private static List<Map<String, String>> books = Arrays.asList(
//            ImmutableMap.of("id", "book-1",
//                    "name", "Harry Potter and the Philosopher's Stone",
//                    "pageCount", "223",
//                    "authorId", "author-1",
//                    "publisher", "人民教育出版社"),
//            ImmutableMap.of("id", "book-2",
//                    "name", "Moby Dick",
//                    "pageCount", "635",
//                    "authorId", "author-2",
//                    "publisher", "中国农业出版社"),
//            ImmutableMap.of("id", "book-3",
//                    "name", "Interview with the vampire",
//                    "pageCount", "371",
//                    "authorId", "author-3",
//                    "publisher", "机械工业出版社")
//    );
//
//    private static List<Map<String, String>> authors = Arrays.asList(
//            ImmutableMap.of("id", "author-1",
//                    "firstName", "Joanne",
//                    "lastName", "Rowling",
//                    "age", "18"),
//            ImmutableMap.of("id", "author-2",
//                    "firstName", "Herman",
//                    "lastName", "Melville",
//                    "age", "19"),
//            ImmutableMap.of("id", "author-3",
//                    "firstName", "Anne",
//                    "lastName", "Rice",
//                    "age", "20")
//    );
//
//    public DataFetcher getBookByIdDataFetcher() {
//        return dataFetchingEnvironment -> {
//            String bookId = dataFetchingEnvironment.getArgument("id");
//            return books
//                    .stream()
//                    .filter(book -> book.get("id").equals(bookId))
//                    .findFirst()
//                    .orElse(null);
//        };
//    }
//
//    public DataFetcher getAuthorDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String,String> book = dataFetchingEnvironment.getSource();
//            String authorId = book.get("authorId");
//            return authors
//                    .stream()
//                    .filter(author -> author.get("id").equals(authorId))
//                    .findFirst()
//                    .orElse(null);
//        };
//    }
//
//    public DataFetcher getPageCountDataFetcher() {
//        return dataFetchingEnvironment -> {
//            Map<String,String> book = dataFetchingEnvironment.getSource();
//            return book.get("totalPages");
//        };
//    }
//}