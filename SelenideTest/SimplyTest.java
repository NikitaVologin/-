import com.codeborne.selenide.Condition;
import org.junit.Before;
import org.junit.Test;

import static com.codeborne.selenide.Selenide.*;

public class SimplyTest {

    @Before
    public void setUp() {
        open("https://xn--80aadqodslk6b8b2d6a.xn--p1ai/");
        $x("//a[@href='/']").click();
    }

    int GetenerateNumber(){
        return (int) (Math.random() * 1000);
    }
    @Test
    public void RegistrationTest(){
        $x("//div[@class='top-menu-wrapper hidden-sm']/a[@class='client-account-link']").click();
        $x("//div[@class='co-form-controls']/a[@class='co-button co-button--link co-form-button'][@href='/client_account/contacts/new']").click();
        $x("//div[@class='co-input co-input--required co-input--text co-input--name  co-input--nested co-input--empty_nested']" +
                "/input[@id='client_name']")
                .setValue("Иванов Иван Иванович");
        $x("//input[@id='client_phone']")
                .setValue("7(926)111-11-11");
        $x("//input[@id='client_email']")
                .setValue("ivanov"+ GetenerateNumber() +"@mail.ru");
        $x("//input[@id='client_password']")
                .setValue("Password123");
        $x("//input[@id='client_password_confirmation']")
                .setValue("Password123");
        $x("//button[@class='co-button co-form-button js-co-login-submit']").click();
        $x("//a[@href='/client_account/login']/span[@class='js-user-name']")
                .shouldHave(Condition.text("Иванов Иван Иванович"));
        sleep(3000);
    }

    @Test
    public void BuyTeaTest() {
        $x("//a[@href='/collection/chay']").click();
        $x("//a[@href='/collection/zelenyy-chay-2']").click();
        $x("//button[@class='button button-buy is-primary']").click();
        $x("//div[@class='popup_button button-popup-form-order  ']").click();
        $x("//input[@id='shipping_address_full_locality_name']")
                .setValue("Новосибирск");
        $x("//textarea[@id='shipping_address_address']")
                .setValue("Улица Пушка, дом Колотушкина");
        $x("//button[@id='create_order']").click();
        $x("//span[@class='Text__StyledTextSpan-sc-9bqqn7-0 gYWUYX mui-as11ew e1vghgsl0']").click();
        $x("//div[@class='co-title co-title--h2']")
                .shouldHave(Condition.text("Информация о заказе"));
        sleep(10000);
    }

    @Test
    public void AddAndDeleteProductFromChosenTest() {
        $x("//a[@href='/collection/chay']").click();
        $x("//a[@href='/collection/zelenyy-chay-2']").click();
        $x("//button[@class='button button-buy is-primary']").click();
        $x("//div[@class='popup_button button-popup-form-cart  ']").click();
        $x("//button[@class='button is-item-delete is-transparent']").click();
        $x("//button[@class='button is-item-delete is-transparent']").shouldNotBe(Condition.visible);
        sleep(3000);
    }

    @Test
    public void FindProductTest() {
        $x("//input[@id='input_search']").setValue("чай");
        $x("//button[@class='search-widget-button button is-widget-submit']").click();
        $x("//h1[@class='page-headding']").shouldBe(Condition.visible);
        sleep(3000);
    }

    @Test
    public void SendResponseTest() {
        $x("//a[@href='/page/feedback']").click();
        $x("//textarea[@id='feedback_content']").setValue("Всё классно!");
        $x("//input[@id='feedback_from']").setValue("ivanov@mail.ru");
        $x("//input[@id='feedback_commit']").click();
        $x("//input[@name='commit']").click();
        $x("//p[@class='notice']").shouldBe(Condition.visible);
        sleep(3000);
    }

    @Test
    public void AddLoveProductTest() {
        $x("//a[@href='/collection/chay']").click();
        $x("//a[@href='/collection/zelenyy-chay-2']").click();
        $x("//i[@class='fa fa-heart-o favorite-card favorite-js favorite-150763710']").click();
        $x("//div[@class='compares-widget  ']").click();
        $x("//a[@href='/product/bay-lun-chzhu-spetspredlozhenie']").shouldBe(Condition.visible);
        sleep(3000);
    }

    @Test
    public void AaaSignOut() {
        $x("//span[@class='js-user-name']").click();
        $x("//a[@href='/client_account/exit']").click();
        $x("//span")
                .shouldHave(Condition.text("вход в личный кабинет"));
        sleep(3000);
    }
}