public POM.XML : 
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.klef.fsad</groupId>
    <artifactId>InventoryHibernateProject</artifactId>
    <version>1.0</version>

    <dependencies>

        <!-- Hibernate Core -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>5.6.15.Final</version>
        </dependency>

        <!-- MySQL Connector -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version>
        </dependency>

        <!-- JPA -->
        <dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>javax.persistence-api</artifactId>
            <version>2.2</version>
        </dependency>

    </dependencies>

</project>






HIBERNATE.XML

<?xml version='1.0' encoding='utf-8'?>

<!DOCTYPE hibernate-configuration PUBLIC
"-//Hibernate/Hibernate Configuration DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-configuration-3.0.dtd">

<hibernate-configuration>

    <session-factory>

        <!-- Database Connection -->

        <property name="hibernate.connection.driver_class">
            com.mysql.cj.jdbc.Driver
        </property>

        <property name="hibernate.connection.url">
            jdbc:mysql://localhost:3306/fsadendexam
        </property>

        <property name="hibernate.connection.username">
            root
        </property>

        <property name="hibernate.connection.password">
            root
        </property>

        <!-- Hibernate Dialect -->

        <property name="hibernate.dialect">
            org.hibernate.dialect.MySQL8Dialect
        </property>

        <!-- Automatically create table -->

        <property name="hibernate.hbm2ddl.auto">
            update
        </property>

        <!-- Show SQL -->

        <property name="hibernate.show_sql">
            true
        </property>

        <!-- Mapping Class -->

        <mapping class="com.klef.fsad.exam.Inventory"/>

    </session-factory>

</hibernate-configuration>




INVENTORY.Java
package com.klef.fsad.exam;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inventory_table")

public class Inventory
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String description;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String status;

    private double price;

    private int quantity;

    // Default Constructor

    public Inventory()
    {

    }

    // Getters and Setters

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Date getDate()
    {
        return date;
    }

    public void setDate(Date date)
    {
        this.date = date;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
}





CLIENT.java

package com.klef.fsad.exam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.Scanner;

public class ClientDemo
{
    public static void main(String[] args)
    {
        Configuration cfg = new Configuration();
        cfg.configure("hibernate.cfg.xml");

        SessionFactory sf = cfg.buildSessionFactory();

        Session session = sf.openSession();

        Scanner sc = new Scanner(System.in);

        Transaction tx = session.beginTransaction();

        System.out.println("1. Insert Record");
        System.out.println("2. Delete Record");

        System.out.print("Enter Choice : ");

        int choice = sc.nextInt();

        switch(choice)
        {
            case 1:

                Inventory inv = new Inventory();

                System.out.print("Enter Name : ");
                sc.nextLine();
                inv.setName(sc.nextLine());

                System.out.print("Enter Description : ");
                inv.setDescription(sc.nextLine());

                System.out.print("Enter Status : ");
                inv.setStatus(sc.nextLine());

                System.out.print("Enter Price : ");
                inv.setPrice(sc.nextDouble());

                System.out.print("Enter Quantity : ");
                inv.setQuantity(sc.nextInt());

                inv.setDate(new Date());

                session.save(inv);

                tx.commit();

                System.out.println("Record Inserted Successfully");

                break;

            case 2:

                System.out.print("Enter ID to Delete : ");

                int id = sc.nextInt();

                Inventory obj = session.get(Inventory.class, id);

                if(obj != null)
                {
                    session.delete(obj);

                    tx.commit();

                    System.out.println("Record Deleted Successfully");
                }
                else
                {
                    System.out.println("Record Not Found");
                }

                break;

            default:

                System.out.println("Invalid Choice");
        }

        session.close();
        sf.close();
    }
}
 {
    
}
