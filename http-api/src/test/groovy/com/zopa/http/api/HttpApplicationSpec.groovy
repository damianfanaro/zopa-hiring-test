package com.zopa.http.api

import spock.lang.Specification

class HttpApplicationSpec extends Specification {

    def 'Calling the entry point'() {

        setup: 'Re-route standard out'
        def buf = new ByteArrayOutputStream(1024)
        System.out = new PrintStream(buf)

        when: 'The entrypoint is executed'
        HttpApplication.main('gradlephant')

        then: 'The correct greeting is output'
        buf.toString() == "Hello, Gradlephant\n".denormalize()
    }

}
