import org.example.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class RegisterTests {

    int min = 1000000;
    int max = 9999999;
    int rndNum = min + (int) Math.round(Math.random() * (max - min));







    @Test
    public void positiveTest(){
        User u = new User("jonas","kisielius","" + rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        String text = "";
        try {
            text = User.driver.findElement(By.className("form-title")).getText();
        }catch (Exception e){}
        Assert.assertEquals(text,"Patvirtinkite paskyrą");
    }

    @Test
    public void noEmailAddressTest(){
        User u = new User("jonas","kisielius","","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailUserNoEtaNoDomainNoTopDomainTest(){
        User u = new User("jonas","kisielius","labas","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailUserEtaDomainNoTopDomainTest(){
        User u = new User("jonas","kisielius","" + rndNum,"6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailWithSpaceInUserTest(){
        User u = new User("jonas","kisielius","labas vakaras@varle.lt","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }

    @Test
    public void emailTooLongTest(){
        User u = new User("jonas","kisielius","labavakara".repeat(60) + "@varle.lt ","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"El. paštas turi būti ilgesnis nei 255 simbolių.");
    }

    @Test
    public void emailWithLeadingTreilingSpacesTest(){
        User u = new User("jonas","kisielius"," labavakara@varle.lt ","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("email"),"Šis adresas yra neteisingas.");
    }
    @Test
    public void phoNoWithLessletters(){
        User u = new User("jonas","kisielius","labavakara@varle.lt ","" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("phoNo"),"Klaidingai įvestas tel. numeris");
    }
    @Test
    public void phoNoWithMoreletters(){
        User u = new User("jonas","kisielius","labavakara@varle.lt ","6895" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("phoNo"),"Klaidingai įvestas tel. numeris");
    }
    @Test
    public void phoNoWithLettersletters(){
        User u = new User("jonas","kisielius","labavakara@varle.lt ","jslsa" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("phoNo"),"Klaidingai įvestas tel. numeris");
    }

    @Test
    public void passwordChecker(){
        User u = new User("Emaxer" + rndNum,"Emaxer" + rndNum, rndNum + "@gmail.com","6" + rndNum,"A");
        u.register();
        Assert.assertEquals(errorMsg("password"),"Slaptažodį privalo sudaryti bent 8 simboliai, iš kurių bent viena didžioji raidė, mažoji raidė ir skaičius");
    }
    @Test
    public void passwordCheckerLongs(){
        User u = new User("Emaxer","Emaxer" + rndNum, rndNum + "@gmail.com","6" + rndNum,"Aa" + rndNum +rndNum +rndNum+ rndNum+ +rndNum +rndNum +rndNum +rndNum +rndNum +rndNum +rndNum +rndNum+ rndNum);
        u.register();
        Assert.assertEquals(errorMsg("password"),"Slaptažodį privalo sudaryti bent 8 simboliai, iš kurių bent viena didžioji raidė, mažoji raidė ir skaičius");
    }
    @Test
    public void loginChecker(){
        User u = new User("Emaxer" + rndNum,"Emaxer" + rndNum, rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("name"),"Jūsų vardą gali sudaryti tik raidės ir „-“ simbolis");
    }
    @Test
    public void loginEmptyChecker(){
        User u = new User("","Emaxer" + rndNum, rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("name"),"Ši reikšmė negali būti tuščia.");
    }
    @Test
    public void loginMinusesChecker(){
        User u = new User("Emaxer--------------------------------------------","Emaxer" + rndNum, rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("name"),"Jūsų vardą gali sudaryti tik raidės ir „-“ simbolis");
    }
    @Test
    public void surnameEmptyChecker(){
        User u = new User("Emaxer--------------------------------------------","", rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("surname"),"Ši reikšmė negali būti tuščia.");
    }
    @Test
    public void surnameLongChecker(){
        User u = new User("Emaxer--------------------------------------------","xbcnxj45546$$$$cfbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbxbcnxj45546$$$$cfbvhbbdgfdfdfgdfgbfgdsbsfgbvhbbdgfdfdfgdfgbfgdsbsfgbsdfgbfsbfgbfgbgfbfgbgfbfgbfgbsfgbsbsbsbsgbsbsgbsf" + rndNum, rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register();
        Assert.assertEquals(errorMsg("surname"),"Per didelis simbolių skaičius. Turi susidaryti iš 255 arba mažiau simbolių.");
    }

    @Test
    public void checkBoxxxChecker(){
        User u = new User("Emaxer--------------------------------------------","Emaxer", rndNum + "@gmail.com","6" + rndNum,"Aa3456789");
        u.register("e");
        Assert.assertEquals(errorMsg("checkbox "),"Privalote sutikti su taisyklėmis");
    }





    @BeforeClass
    public void beforeClass(){
        User.driver = new ChromeDriver();
        User.driver.manage().window().maximize();
        User.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        acceptCookies();
    }

    public String errorMsg(String field){
        String errorMsg = "";
        int position = 0;

        switch (field){
            case "name":
                position = 0;
                break;
            case "surname":
                position = 1;
                break;
            case "email":
                position = 2;
                break;
            case "phoNo":
                position = 3;
                break;
            case "password":
                position = 4;
                break;
            case "checkbox":
                position = 5;
                break;
        }
        try {
            errorMsg = User.driver.
                    findElement(By.className("login-page-body--form")).
                    findElements(By.className("field-col")).get(position).
                    findElement(By.className("field-error")).getText();
        }catch (Exception e){}
        return errorMsg;
    }
    public void acceptCookies(){
        User.driver.get("https://www.livinn.lt/register");
        User.driver.findElement(By.id("onetrust-button-group")).click();
    }

    @AfterClass
    public void afterClass(){
      driver.close();
    }
}