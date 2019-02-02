package com.zopa

import com.zopa.http.api.Executable
import ratpack.test.MainClassApplicationUnderTest
import spock.lang.Shared
import spock.lang.Specification

class ExecutableSpec extends Specification {

    @Shared
    MainClassApplicationUnderTest appUnderTest = new MainClassApplicationUnderTest(Executable.class)

    def 'Calling server at root path'() {

        given: 'An expected response message'
        def expectedResponseMessage = "Welcome to Zopa Loan Calculator!"

        when: 'Performing a GET request to /'
        def responseMessage = appUnderTest.getHttpClient().getText("/")

        then: 'The correct message is replied'
        expectedResponseMessage == responseMessage

    }

}
