Feature: Completar una compra

  Background:
    Given Abrir Swag Labs
    When  Ingreso el usuario "standard_user" en el campo username
    And   Ingreso la contraseña "secret_sauce" en el campo password
    And   Presiono el boton "login"
    Then  Login exitoso

  Scenario: El usuario intenta comprar productos después de ordenar de la A a la Z

    When  Quiero ordenar
    And   Selecciono por "A to Z"
    Then  Los productos son ordenados de "A to Z"

    When  Presiono añadir al carrito
    Then  El numero cambia a "1"

    When  Presiono el icono del carrito
    Then  Me redirecciona de la pagina

    When  Presiono el botón Check out
    Then  Me redirecciona a mis datos

    When  Ingreso el nombre "Miguel" , apellido "Livia" y codigo postal "1234"
    And   Presiono continuar
    Then  Redirecciona a la vista de compra
    And   Verifico precio 29.99

    When  Presiono finalizar
    Then  Orden completada
    And  Su pedido se registro

########################################################################################################################

  Scenario: El usuario intenta comprar productos después de ordenar de la Z a la A

    When  Quiero ordenar
    And   Selecciono por "Z to A"
    Then  Los productos son ordenados de "Z to A"

    When  Presiono añadir al carrito
    Then  El numero cambia a "1"

    When  Presiono el icono del carrito
    Then  Me redirecciona de la pagina

    When  Presiono el botón Check out
    Then  Me redirecciona a mis datos

    When  Ingreso el nombre "Miguel" , apellido "Livia" y codigo postal "1234"
    And   Presiono continuar
    Then  Redirecciona a la vista de compra
    And   Verifico precio 29.99

    When  Presiono finalizar
    Then  Orden completada
    And  Su pedido se registro
    ##################################################################################
