package com.example;

import java.util.Optional;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CartResource {

  @Autowired
  private CartRepository repository;
  /*
   * Construtor do CartResource, preparando uma lista de produtos
   */
  public CartResource(CartRepository repository) {
    this.repository = repository;
  }

  /**
   * Metodo de requisicao do tipo GET, para uma lista
   * @param raca tipo de raca para filtrar
   * @return lista de produtos, filtrados ou nao
   */
  @RequestMapping(value = "/compras/", method = RequestMethod.GET)
  public Iterable<Cart> buscarProdutos(@RequestParam(required = false) String raca) {
    return this.repository.findAll();
  }

  /**
   * Metodo de requisicao do tipo GET, para um item
   * @param id identificador ou indice da colecao dos produtos
   * @return item de produto unico
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.GET)
  public Optional<Cart> buscarProduto(@PathVariable Long id) {
    return this.repository.findById(id);
  }
  
  /**
   * Metodo de requisicao do tipo DELETE, para remover um item
   * @param id identificador ou indice da colecao dos produtos
   */
  @RequestMapping(value = "/compras/{id}", method = RequestMethod.DELETE)
  public void removerProduto(@PathVariable Long id) {
    this.repository.deleteById(id);
  }

  @RequestMapping(value = "/compras/", 
  method = RequestMethod.POST)
  public Cart criarProduto(@RequestBody Cart Cart) {
    Product produto = Cart.getProduct();
    Customer cliente = Cart.getCustomer();
    return this.repository.save(new Cart(produto, cliente));
  }

  @RequestMapping(value="/compras/{id}", 
  method=RequestMethod.PUT)
  public void alterarProduto(@PathVariable Long id,
  @RequestBody Cart produtoParam) {
      Cart cliente = this.repository.findById(id).get();
      cliente.setProduct(produtoParam.getProduct());
      cliente.setCustomer(produtoParam.getCustomer());
  }

}
