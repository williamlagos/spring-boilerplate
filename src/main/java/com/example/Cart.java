package com.example;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cart {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @ManyToOne
  private Product product;
  @ManyToOne
  private Customer customer;


  protected Cart() {}

  public Cart(Product product, Customer customer) {
    this.product = product;
    this.customer = customer;
  }

  @Override
  public String toString() {
    return String.format(
        "[id=%d, customer='%s', product='%s']",
        id, customer, product);
  }

  public Long getId() {
    return id;
  }

  /**
   * @return the customer
   */
  public Customer getCustomer() {
    return customer;
  }

  /**
   * @return the product
   */
  public Product getProduct() {
    return product;
  }

  /**
   * @param customer the customer to set
   */
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  /**
   * @param product the product to set
   */
  public void setProduct(Product product) {
    this.product = product;
  }
}