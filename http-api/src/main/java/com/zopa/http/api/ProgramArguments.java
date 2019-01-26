package com.zopa.http.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
final class ProgramArguments {

    private final String marketFilePath;
    private final long requestedLoan;

}
