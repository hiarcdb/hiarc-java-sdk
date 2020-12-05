package com.hiarcdb.client.integrationTests;

import com.hiarcdb.client.ApiException;
import com.hiarcdb.client.model.LegalHold;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LegalHoldTest {
    private final HiarcTestUtil util = new HiarcTestUtil();

    @Before
    public void setup() {
        try {
            util.admin.resetDB();
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    /**
     * Legal Hold CRUD
     *
     * 
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void legalHoldCRUDTest() throws ApiException {
        LegalHold lh1 = util.legalHolds.createLegalHold(util.createLegalHold());
        LegalHold glh = util.legalHolds.getLegalHold(lh1.getKey());
        Assert.assertEquals(lh1, glh);
    }
}
