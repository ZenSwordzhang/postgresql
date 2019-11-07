package com.zsx.graphql;

import com.zsx.graphql.interfaces.ProductCostInfo;
import com.zsx.graphql.interfaces.ProductDTO;
import com.zsx.graphql.interfaces.ProductInfo;
import com.zsx.graphql.interfaces.ProductTaxInfo;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductDataFetchers {

    void productsDataFetcher() {

        DataFetcher productsDataFetcher = new DataFetcher() {
            @Override
            public Object get(DataFetchingEnvironment env) {
                String matchArg = env.getArgument("match");

                List<ProductInfo> productInfo = getMatchingProducts(matchArg);

                List<ProductCostInfo> productCostInfo = getProductCosts(productInfo);

                List<ProductTaxInfo> productTaxInfo = getProductTax(productInfo);

                return mapDataTogether(productInfo, productCostInfo, productTaxInfo);
            }
        };
    }

    private Object mapDataTogether(List<ProductInfo> productInfo, List<ProductCostInfo> productCostInfo, List<ProductTaxInfo> productTaxInfo) {
        return null;
    }

    private List<ProductTaxInfo> getProductTax(List<ProductInfo> productInfo) {
        return null;
    }

    private List<ProductCostInfo> getProductCosts(List<ProductInfo> productInfo) {
        return null;
    }

    private List<ProductInfo> getMatchingProducts(String matchArg) {
        return null;
    }

    private List<Map> mapDataTogetherViaMap(List<ProductInfo> productInfo, List<ProductCostInfo> productCostInfo, List<ProductTaxInfo> productTaxInfo) {
        List<Map> unifiedView = new ArrayList<>();
        for (int i = 0; i < productInfo.size(); i++) {
            ProductInfo info = productInfo.get(i);
            ProductCostInfo cost = productCostInfo.get(i);
            ProductTaxInfo tax = productTaxInfo.get(i);

            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("id", info.getId());
            objectMap.put("name", info.getName());
            objectMap.put("description", info.getDescription());
            objectMap.put("cost", cost.getCost());
            objectMap.put("tax", tax.getTax());

            unifiedView.add(objectMap);
        }
        return unifiedView;
    }

    private List<ProductDTO> mapDataTogetherViaDTO(List<ProductInfo> productInfo, List<ProductCostInfo> productCostInfo, List<ProductTaxInfo> productTaxInfo) {
        List<ProductDTO> unifiedView = new ArrayList<>();
        for (int i = 0; i < productInfo.size(); i++) {
            ProductInfo info = productInfo.get(i);
            ProductCostInfo cost = productCostInfo.get(i);
            ProductTaxInfo tax = productTaxInfo.get(i);

            ProductDTO productDTO = new ProductDTO(
                    info.getId(),
                    info.getName(),
                    info.getDescription(),
                    cost.getCost(),
                    tax.getTax()
            );
            unifiedView.add(productDTO);
        }
        return unifiedView;
    }
}
