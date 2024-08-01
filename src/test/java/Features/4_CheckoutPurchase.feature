Feature: Revision del carrito
  Background:
    Given Abrir Swag Labs
    When  Ingreso el usuario "standard_user" en el campo username
    And   Ingreso la contraseña "secret_sauce" en el campo password
    And   Presiono el boton "login"
    Then  Login exitoso

  Scenario: Comprobar intento de pago con el carrito vacío
    When  Presiono el icono del carrito
    Then  Me redirecciona de la pagina

    When  Presiono el botón Check out
    Then  Rechaza por datos invalidos


  Scenario: Validacion de datos invalidados durante el proceso de pago
    When  Presiono añadir al carrito
    Then  El numero cambia a "1"

    When  Presiono el icono del carrito
    Then  Me redirecciona de la pagina

    When  Presiono el botón Check out
    Then  Me redirecciona a mis datos

    When  Ingreso el nombre "" , apellido "" y codigo postal ""
    And   Presiono continuar
    Then  Valido mensaje de error "Error: First Name is required"

