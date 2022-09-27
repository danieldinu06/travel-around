package com.codecool.shop.service;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;

public class FileHandler {
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public FileHandler() {

    }

    public File getCategoriesFile() {
        return new File("src/main/resources/data/categories.json");
    }

    public File getProductsFile() {
        return new File("src/main/resources/data/products.json");
    }

    public File getSupplierFile() {
        return new File("src/main/resources/data/suppliers.json");
    }

    public String exportCategories() {
        ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
        return gson.toJson(productCategoryDao);
    }

    public String exportProducts() {
        ProductDao productDao = ProductDaoMem.getInstance();
        return gson.toJson(productDao);
    }

    public String exportSuppliers() {
        SupplierDao supplierDao = SupplierDaoMem.getInstance();
        return gson.toJson(supplierDao);
    }
}
