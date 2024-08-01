Feature: Comprar varios productos

  Background:
    Given Abrir Swag Labs
    When  Ingreso el usuario "standard_user" en el campo username
    And   Ingreso la contraseña "secret_sauce" en el campo password
    And   Presiono el boton "login"
    Then  Login exitoso

  Scenario: El usuario intenta comprar 3 productos
    When  Presiono añadir al carrito
    And   Presiono añadir el producto Sauce Labs Bike Light
    And   Presiono añadir el producto Sauce Labs Bolt T-Shirt
    Then  El numero cambia a "3"

    When  Presiono el icono del carrito
    Then  Me redirecciona de la pagina

    When  Presiono el botón Check out
    Then  Me redirecciona a mis datos

    When  Ingreso el nombre "Miguel" , apellido "Livia" y codigo postal "1234"
    And   Presiono continuar
    Then  Redirecciona a la vista de compra
    And   Verifico precio 55.97

    When  Presiono finalizar
    Then  Orden completada
    And  Su pedido se registro