package com.calvin.assesment.drones.service.impl;

import com.calvin.assesment.drones.service.FileWriterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
@Slf4j
public class FileWriterServiceImpl implements FileWriterService {

    @Override
    public void writeToLog(String message, String fileName) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter(fileName, true);
            bw = new BufferedWriter(fw);

            bw.write(message);
            bw.newLine();
            System.out.println(message);
        } catch (IOException e) {
            log.info("Failed to write to {} file at {}", fileName, LocalDateTime.now());
        } finally {
            try {
                if (bw != null)
                    bw.close();

                if (fw != null)
                    fw.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
