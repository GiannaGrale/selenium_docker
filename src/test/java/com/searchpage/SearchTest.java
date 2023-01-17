package com.searchpage;

import com.BaseTest;
import com.pages.searchmodule.SearchPage;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class SearchTest extends BaseTest {

    @Test
    @Parameters({"keyword"})
    public void search(@Optional("2") String keyword) {
        SearchPage searchPage = new SearchPage(driver);
        searchPage.goTo();
        searchPage.doSearch(keyword);
        searchPage.goToVideos();
        int size = searchPage.getResult();
        assertTrue(size > 0);
    }
}
