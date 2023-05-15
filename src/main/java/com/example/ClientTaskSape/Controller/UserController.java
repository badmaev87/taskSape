package com.example.ClientTaskSape.Controller;

import com.example.ClientTaskSape.Entity.Client;
import com.example.ClientTaskSape.Repository.ClientJDBCRepository;
import com.opencsv.bean.CsvToBean;

import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    ClientJDBCRepository clientJDBCRepository;


    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/upload")
    public ModelAndView uploadCSVFile(@RequestParam("file")MultipartFile file, Model model) throws IOException {

        // validate file
        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            // parse CSV file to create a list of `User` objects
            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

                // create csv bean reader
                CsvToBean<Client> csvToBean = new CsvToBeanBuilder(reader)
                        .withType(Client.class)
                        .withIgnoreLeadingWhiteSpace(true)
                        .build();

                // convert `CsvToBean` object to list of users
                List<Client> clientList = csvToBean.parse();
                System.out.println(clientList);

                // TODO: save users in DB
                for (Client client: clientList) {
                    logger.info("Inserting -> {}", clientJDBCRepository.insert(client.getName(), client.getAge(), client.getGroupId(), client.getPhone(),client.getDate()));

                }

                // save users list on model
                model.addAttribute("client", clientList);
                model.addAttribute("status", true);
            } catch (Exception exception) {
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return getAllUsers();
    }

    @GetMapping("/list")
    public ModelAndView getAllUsers() {
        ModelAndView mav = new ModelAndView("list-clients");
        mav.addObject("clients", clientJDBCRepository.findAll());
        return mav;
    }
}
