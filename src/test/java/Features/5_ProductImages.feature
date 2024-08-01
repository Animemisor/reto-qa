Feature: Validar imagenes de productos

  Background:
    Given Abrir Swag Labs
    When  Ingreso el usuario "standard_user" en el campo username
    And   Ingreso la contraseña "secret_sauce" en el campo password
    And   Presiono el boton "login"
    Then  Login exitoso

  Scenario: Validacion de imagenes de productos correctos
    When  Presiono añadir al carrito
    Then  El numero cambia a "1"
    And   Valido la imagen

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