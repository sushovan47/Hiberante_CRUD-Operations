package com;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateDemo 
{
		//Create or Add a Product To Database Using addProduct() Method
	public void addProduct(SessionFactory sessionFactory)
	{
		Session session =sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product Id:");
		int productId=sc.nextInt();
		System.out.println("Enter Product Name:");
		String productName=sc.next();
		System.out.println("Enter Product Price:");
		int price=sc.nextInt();
		
		com.Product product=new com.Product();
		product.setProductId(productId);
		product.setProductName(productName);
		product.setPrice(price);
		
		session.save(product);
		transaction.commit();
		session.close();
		
		System.out.println("Data Stored in the DataBase Successfully....");	
		
	}
		//Retrive a Product From Database Using retrieveProduct() Method
	public Product retrieveProduct(SessionFactory sessionFactory)
	{
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the productId:");
		int productId=sc.nextInt();
		
		Session session=sessionFactory.openSession();
		Product product =(Product) session.get(Product.class,productId);
		session.close();
		System.out.println("Retrieve Data Successfully....");
		return product;
		
	}
		//If you want to display any Product from Database then displayProduct() Meyhod Using
	private void displayProduct(SessionFactory sessionFactory) 
	{
		Session session=sessionFactory.openSession();
		Query query=session.createQuery("from Product");
		List<Product> listProducts=query.list();
		
		for(Product product:listProducts)
		{
			System.out.print(product.getProductId()+":::");
			System.out.print(product.getProductName()+":::");
			System.out.println(product.getPrice());
		}			
	}
		//Update a product in the Database
	public void updateProduct(SessionFactory sessionFactory) 
	{
		Product product =this.retrieveProduct(sessionFactory);
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Product Id");
		int price=sc.nextInt();
		System.out.println("Enter Product Name");
		String productName=sc.next();
		
		product.setPrice(price);
		product.setProductName(productName);
		
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		
		session.save(product);
		
		transaction.commit();
		
		session.close();
		System.out.println("Data Updated Successfully....");
	}
			//Delete a Product or row from the Database
	private void deleteProduct(SessionFactory sessionFactory) 
	{
		Product product=this.retrieveProduct(sessionFactory);
		Session session=sessionFactory.openSession();
		Transaction transaction=session.beginTransaction();
		session.delete(product);
		transaction.commit();
		session.close();
		
		System.out.println("Data Deleted Successfully........");	
	}
			
			//This is the Main Method if you want to store any data manually then write given code .
	public static void main(String arg[])
	{
		//Step-->1  Create the Configuration of the Hibernate Properties Which is Created in "hibernate.cfg.xml" file 
		Configuration config=new Configuration();
		config.configure("hibernate.cfg.xml");
		
		//Step-->2 Basically Connection to the DataBase whichever you using ,communicate with database. And it's an instance of Configuration() Class
		SessionFactory sessionFactory=config.buildSessionFactory();
		
		//Creating the Main Class Object Just for Checking all the above methods.
		HibernateDemo obj=new HibernateDemo();
		Scanner sc=new Scanner(System.in);
		char ch;
		do
		{
			System.out.println("1.Add Product  2.Display Using Get  3.Update Product  4.Delete Product  5.Display Product");
			ch=sc.next().charAt(0);
			switch (ch)
			{
			case'1':
				obj.addProduct(sessionFactory);
				break;
			case'2':
				Product product=obj.retrieveProduct(sessionFactory);
				System.out.println("Product id:"+product.getProductId());
				System.out.println("Product Name:"+product.getProductName());
				System.out.println("Product Price:"+product.getPrice());
				break;
			case'3':
				obj.updateProduct(sessionFactory);
				break;
			case '4':
				obj.deleteProduct(sessionFactory);
				break;
			case '5':
				obj.displayProduct(sessionFactory);
				break;
			}
		}
		while(ch<=5);			
			
		//Step-->3 In one line it's a pipeline b/w Database and the Application.Also used the instance of SessionFactory Object.
		Session session=sessionFactory.openSession();
		
		//Step-->4 Logical unit of work from the Database and also used for the consistency in the database.Also It's an instance of Session Object.
		Transaction transaction=session.beginTransaction();
		
		//Now Create the Model Class Object just because of storing the data to the database.
		com.Product product=new com.Product();
		
		
		/*product.setProductId(1001);
		product.setProductName("Samsung J6");
		product.setPrice(7089);*/
		
		//Step-->5 It is a main function to store the data into the database.using the instance of session object with save() method.
		session.save(product);
		
		//Committing Because of the all the transaction are is successfull.
		transaction.commit();
		
		//Close the Connection 
		session.close();
		
	}
	
	
}
	