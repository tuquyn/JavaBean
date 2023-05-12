package com.example.demo;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShoppingServlet", value = "/ShoppingServlet")
public class ShoppingServlet extends HttpServlet {
    private AccountBean acc;
    private List<List<String>> productList;
    private String total;
    private int productId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");

        if(request.getParameter("submit") != null && request.getParameter("submit").equals("Submit")){
            acc = null;
            productList = null;
            total = "0";
            productId = 0;
        }

        createAcc(request, response);

        deleteProductFromProductList(request, response);

        createProductList(request, response);

        request.setAttribute("acc", acc);
        request.setAttribute("productList", productList);
        request.setAttribute("total", total);

        if (request.getParameter("checkout") == null){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/products.jsp");
            dispatcher.forward(request, response);
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/checkout.jsp");
            dispatcher.forward(request, response);
        }
    }
    private void createAcc(HttpServletRequest request, HttpServletResponse response){
        if(this.acc == null){
            acc = new AccountBean();
            acc.setName(request.getParameter("name"));
            acc.setVisaCardNum(Integer.parseInt(request.getParameter("visaCardNum")));
            acc.setAddress(request.getParameter("address"));
        }
    }

    private void createProductList(HttpServletRequest request, HttpServletResponse response){
        boolean isDone = false;
        if(productList!= null && request.getParameter("submit") != null){
            if(request.getParameter("submit").equals("Add to cart") ){
                List<String> currentProduct = parseProductValue(request, response);
                for(List<String> product: productList){
                    System.out.println(product);

                    if(Integer.parseInt(product.get(1)) == Integer.parseInt(currentProduct.get(1))){
                        List<String> prod = new ArrayList<>();
                        for(int i = 0; i < 5; i++){
                            prod.add(product.get(i));
                        }
                        prod.add(String.valueOf(Integer.parseInt(product.get(5)) + Integer.parseInt(currentProduct.get(5))));
                        prod.add(String.format("%.2f",Float.parseFloat(product.get(6)) + Float.parseFloat(currentProduct.get(6))));

                        productList.remove(product);
                        productList.add(prod);
                        isDone = true;
                        break;
                    }
                }
                if(!isDone){
                    productList.add(currentProduct);
                }
                total = getCurrentTotal(productList);

            }
        }
        if(this.productList == null ) {
            productList = new ArrayList<>();
        }

        request.setAttribute("productList", productList);
        request.setAttribute("total", total);
    }

    private void deleteProductFromProductList(HttpServletRequest request, HttpServletResponse response) throws IOException{

        if(request.getParameter("submit") != null){
            if(request.getParameter("submit").equals("Delete")){
                int productId = Integer.parseInt(request.getParameter("idDel"));
                for(List<String> product: productList){
                    if(Integer.parseInt(product.get(0)) == productId){
                        productList.remove(product);
                        break;
                    }
                }
                total = getCurrentTotal(productList);
            }
        }

    }

    private List<String> parseProductValue(HttpServletRequest request, HttpServletResponse response){
        List<String> result = new ArrayList<>();
        Float subTotal;

        result.add(String.valueOf(productId++));
        String productValues = request.getParameter("product");
        String[] productValuesArr = productValues.split("-");
        for (String productValue : productValuesArr) {
            result.add(productValue);
        }
        result.add(request.getParameter("quantity"));
        subTotal = Float.parseFloat(result.get(4)) * Float.parseFloat(result.get(5));
        result.add(String.format("%.2f",subTotal));
        return result;
    }

    private String getCurrentTotal(List<List<String>> productList){
        float total = 0;
        for (List<String> product : productList) {
            total += Float.parseFloat(product.get(6));
        }
        return String.format("%.2f",total);
    }
}
