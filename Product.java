package com;

public class Product 		//Model Class of the Hibernate which data you want to Store
{
	int productId;
	String productName;
	int price;
	
	public int getProductId() 
	{
		return productId;
	}
	public void setProductId(int productId) 
	{
		this.productId = productId;
	}
	
	public String getProductName() 
	{
		return productName;
	}
	public void setProductName(String productName) 
	{
		this.productName = productName;
	}
	
	public int getPrice() 
	{
		return price;
	}
	public void setPrice(int price) 
	{
		this.price = price;
	}
	
}
