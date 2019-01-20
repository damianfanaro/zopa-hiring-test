package com.zopa.http.api

import ratpack.test.MainClassApplicationUnderTest
import spock.lang.Shared
import spock.lang.Specification

class HttpApplicationSpec extends Specification {

    @Shared
    MainClassApplicationUnderTest appUnderTest = new MainClassApplicationUnderTest(HttpApplication.class)

    def 'Calling server at root path'() {
        when: 'Performing a GET request to /'
        def responseMessage = appUnderTest.getHttpClient().getText("/")

        then: 'The correct message is replied'
        "Welcome to Zopa Loan Calculator!" == responseMessage
    }

}
