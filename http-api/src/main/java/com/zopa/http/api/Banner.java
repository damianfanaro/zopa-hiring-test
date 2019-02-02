package com.zopa.http.api;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import ratpack.service.Service;
import ratpack.service.StartEvent;
import ratpack.service.StopEvent;

import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class Banner implements Service {

    @Override
    public void onStart(StartEvent event) {
        showBanner();
    }

    @Override
    public void onStop(StopEvent event) {
        log.info("Stopping Zopa Loan System...");
    }

    @SneakyThrows
    private void showBanner() {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource("banner.txt");
        URI uri = Objects.requireNonNull(resource).toURI();
        Path path = Paths.get(uri);
        Stream<String> lines = Files.lines(path);
        String data = lines.collect(Collectors.joining("\n"));
        lines.close();
        log.info(data);
    }

}
