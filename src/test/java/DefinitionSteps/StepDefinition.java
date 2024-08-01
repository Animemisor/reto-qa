package DefinitionSteps;

import Page.*;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StepDefinition {
    WebDriver driver = new ChromeDriver();
    LoginPagePOM pomLoginPage = new LoginPagePOM(driver);
    HomePage pomHomePage = new HomePage(driver);
    YourCart pomYourCart = new YourCart(driver);
    CheckoutYourInformation pomCheckout = new CheckoutYourInformation(driver);
    CheckoutOverview pomCheckoverview = new CheckoutOverview(driver);
    CheckoutComplete pomComplete = new CheckoutComplete(driver);

    @Given("^Abrir Swag Labs$")
    public void openSwagWebsite() {
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");
    }

    @When("^Ingreso el usuario \"(.*)\" en el campo username$")
    public void enterUserName(String username) {
        pomLoginPage.userNAme().sendKeys(username);
    }

    @And("^Ingreso la contraseña \"(.*)\" en el campo password$")
    public void enterPassword(String password) {
        pomLoginPage.passWord().sendKeys(password);
    }

    @And("Presiono el boton \"login\"")
    public void pressLogin() {
        pomLoginPage.passWord().sendKeys(Keys.ENTER);
    }

    @Then("Login exitoso")
    public void assertLogin() {
        String expectedUrl = "https://www.saucedemo.com/inventory.html";
        String actualUrl = pomHomePage.pageUrl();
        Assert.assertEquals("home page url", expectedUrl, actualUrl);
    }

    @When("^Quiero ordenar$")
    public void pressSortingDropMenu() {
        pomHomePage.sortingDropDown().click();
    }

    @And("Selecciono por {string}")
    public void NameAtoZPress(String type) {
        switch (type) {
            case "A to Z":
                pomHomePage.nameAtoZ().click();
                break;
            case "Z to A":
                pomHomePage.nameZtoA().click();
                break;
            default:
                Assert.assertTrue("wrong name choose from sort table", false);
        }

    }

    @Then("Los productos son ordenados de {string}")
    public void assertSort(String type) {
        String s1 = pomHomePage.fistProduct().getText();
        double s1Price = Double.parseDouble(pomHomePage.fistProductPrice().getText().replace("$", ""));
        String s2 = pomHomePage.secondProduct().getText();
        double s2Price = Double.parseDouble(pomHomePage.secondProductPrice().getText().replace("$", ""));
        String s3 = pomHomePage.thirdProduct().getText();
        double s3Price = Double.parseDouble(pomHomePage.thirdProductPrice().getText().replace("$", ""));
        int compareResult1 = s1.compareTo(s2);
        int compareResult2 = s2.compareTo(s3);
        switch (type) {
            case "A to Z":
                Assert.assertTrue("asser a to z selected", pomHomePage.nameAtoZ().isSelected());
                Assert.assertTrue("A to Z assert switch", compareResult1 < 0 && compareResult2 < 0);
                break;
            case "Z to A":
                Assert.assertTrue("assert z to a selected", pomHomePage.nameZtoA().isSelected());
                Assert.assertTrue("Z to A assert Switch", compareResult1 > 0 && compareResult2 > 0);
                break;
            default:
                Assert.assertTrue("wrong name choose from sort table", false);
        }

    }


    @When("^Presiono añadir al carrito$")
    public void addSauceLabsBackpack() {
        try {
            pomHomePage.sauceLabsBackpackAdd().click();
        }
        catch (Exception e){
            Assert.assertTrue("there is no element for Sauce Labs Backpack",false);
        }
    }

    @And("^Presiono añadir el producto Sauce Labs Bike Light$")
    public void addSauceLabsBikeLight() {
        pomHomePage.sauceLabsBikeLightAdd().click();
    }

    @And("^Presiono añadir el producto Sauce Labs Bolt T-Shirt$")
    public void addSauceLabsBoltTShirt() {
        pomHomePage.sauceLabsBoltTShirt().click();
    }

    @Then("El numero cambia a {string}")
    public void assertCartnumber(String cartNumber) {
        Assert.assertTrue(pomHomePage.cartNumber().getText().contains(cartNumber));
    }
    @And("Valido la imagen")
    public void checkSauceLabsBackpackImage(){
        String actualImageUrl=pomHomePage.sauceLabsBackpackImage().getAttribute("src");
        String expectedImageUrl="/static/media/sauce-backpack-1200x1500.0a0b85a3.jpg";
        Assert.assertTrue(actualImageUrl.contains(expectedImageUrl));
    }

    @When("Presiono el icono del carrito")
    public void cartIconPress() {
        pomHomePage.cartIcon().click();
    }

    @Then("Me redirecciona de la pagina")
    public void cartpageAssert() {
        String expectedUrl = "https://www.saucedemo.com/cart.html";
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals("cartPageurl", expectedUrl, actualResult);
        String expectedTitle = "Your Cart";
        String actualTitle = pomYourCart.cartPageTitle().getText();
        Assert.assertEquals("cart page title", expectedTitle, actualTitle);
    }

    @When("Presiono el botón Check out")
    public void checkOutPress() {
        pomYourCart.checkOut().click();
    }

    @Then("Me redirecciona a mis datos")
    public void checkoutInformationRedirectionAssert() {
        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedURL, actualUrl);
    }
    @Then("Rechaza por datos invalidos")
    public void refuseCheckOut(){
        String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertNotEquals(expectedURL, actualUrl);
    }

    @When("Ingreso el nombre {string} , apellido {string} y codigo postal {string}")
    public void enterNameLastnamePostalcode(String name, String lastNAme, String postalCode) {
        pomCheckout.firstnameField().sendKeys(name);
        pomCheckout.lastnameField().sendKeys(lastNAme);
        pomCheckout.postalcodeField().sendKeys(postalCode);
    }

    @And("Presiono continuar")
    public void clickContinue() {
        pomCheckout.continuebutton().click();
    }
    @Then("Valido mensaje de error {string}")
    public void checkErrorMessage(String errorMessage){
        String expectedErrorMessage="Error: First Name is required";
        String actualErrorMessage = pomCheckout.errorMessage().getText();
        Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage));
    }

    @Then("Redirecciona a la vista de compra")
    public void assertRedirection() {
        String expectedUrl = "https://www.saucedemo.com/checkout-step-two.html";
        String actualUrl = pomCheckoverview.pageUrl();
        Assert.assertEquals("checkout overview page url", actualUrl, expectedUrl);
    }

    @And("Verifico precio {double}")
    public void assertPrice(double price) {
        String expectedprice = String.valueOf(price);
        String actualprice = pomCheckoverview.totalprice().getText();
        Assert.assertTrue(actualprice.contains(expectedprice));
    }

    @When("Presiono finalizar")
    public void pressFisish() {
        pomCheckoverview.finish().click();
    }

    @Then("Orden completada")
    public void checkCompleteTitle() {
        String expectedTitle = "Checkout: Complete!";
        String actualTitle = pomComplete.pageTitle();
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @And("Su pedido se registro")
    public void thankyouAssert() {
        String expectedThank = "Thank you for your order!";
        String actualThank = pomComplete.thankYouMessage();
        Assert.assertTrue(actualThank.contains(expectedThank));
    }

    @When("Selecciono el producto que se eliminara del carrito {string}")
    public void removeProductFromCartPage(String product) {
        switch (product) {
            case "Sauce Labs Bolt T-Shirt":
                pomYourCart.removeSauceLabsBoltTShirt().click();
                break;
            default:
                Assert.assertTrue("wrong name choose from products to remove", false);
        }
    }
    @Then("Se quita el producto {string}")
    public void assertProductDisappearAfterRemove(String product){
        try{
            pomYourCart.removeSauceLabsBoltTShirt();
            Assert.assertTrue("assert fail to remove product",false);
        }
        catch (Exception e){
            Assert.assertTrue("assert should success to remove product",true);
        }
    }

    @AfterStep
    public void afterStep() throws InterruptedException {
        Thread.sleep(10);
    }

    @After
    public void after() throws Exception {
        Thread.sleep(1000);
        driver.quit();
    }
}
