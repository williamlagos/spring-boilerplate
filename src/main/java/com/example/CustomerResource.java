package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CustomerResource {

  @Autowired
  private CustomerRepository repository;
  /*
   * Construtor do CustomerResource, preparando uma lista de clientes
   */
  public CustomerResource(CustomerRepository repository) {
    this.repository = repository;
  }

  /**
   * Metodo de requisicao do tipo GET, para uma lista
   * @return lista de clientes, filtrados ou nao
   */
  @RequestMapping(value = "/clientes/", method = RequestMethod.GET)
  public Iterable<Customer> buscarProdutos() {
    return this.repository.findAll();
  }

  /**
   * Metodo de requisicao do tipo GET, para um item
   * @param id identificador ou indice da colecao dos clientes
   * @return item de produto unico
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.GET)
  public Optional<Customer> buscarCliente(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   * Metodo de requisicao do tipo DELETE, para remover um item
   * @param id identificador ou indice da colecao dos clientes
   */
  @RequestMapping(value = "/clientes/{id}", method = RequestMethod.DELETE)
  public void removerCliente(@PathVariable Long id) {
    this.repository.deleteById(id);
  }

  @RequestMapping(value = "/clientes/", 
  method = RequestMethod.POST)
  public Customer criarCliente(@RequestBody Customer Customer) {
    String nome = Customer.getFirstName();
    String sobrenome = Customer.getLastName();
    return this.repository.save(new Customer(nome, sobrenome));
  }

  @RequestMapping(value="/clientes/{id}", 
  method=RequestMethod.PUT)
  public void alterarCliente(@PathVariable Long id,
  @RequestBody Customer produtoParam) {
      Customer cliente = this.repository.findById(id).get();
      cliente.setFirstName(produtoParam.getFirstName());
      cliente.setLastName(produtoParam.getLastName());
  }

}
