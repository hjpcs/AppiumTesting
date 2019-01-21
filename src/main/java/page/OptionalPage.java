package page;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class OptionalPage extends BasePage {
    private By searchButton=By.id("action_create_cube"); // 自选页搜索按钮
    private By searchText=By.id("search_input_text"); // 搜索页搜索框
    private By addButton=By.id("follow_btn"); // +号按钮

    private By setOptionalButton=text("设自选");
    private By removeOptionalButton=text("删除自选");

    public OptionalPage addOptional(String keyword){
        find(searchButton).click();
        find(searchText).sendKeys(keyword);
        find(addButton).click();
        return this;
    }

    public OptionalPage removeOptional(String keyword){
        //By removeStock=text(keyword);
        //find(removeStock).click();
        for (WebElement stock : Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"))){
            if (stock.getText().equals(keyword)){
                stock.click();
            }
        }
        find(setOptionalButton).click();
        find(removeOptionalButton).click();
        return this;
    }

    public void back(){
        find(By.xpath("//*[contains(@resource-id, 'action_back') or contains(@resource-id, 'action_close')]")).click();
    }

    public ArrayList<String> getStockList(){
        ArrayList<String> arrayList = new ArrayList<String>();
        for (WebElement stock:Driver.getCurrentDriver().findElements(By.id("portfolio_stockName"))){
            arrayList.add(stock.getText());
        }
        return arrayList;
    }
}
