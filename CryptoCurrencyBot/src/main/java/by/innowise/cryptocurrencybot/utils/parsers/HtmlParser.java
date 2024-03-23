package by.innowise.cryptocurrencybot.utils.parsers;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;

@Component
public class HtmlParser implements MessageParser {

    @Override
    public String readMessage(String path){
        StringBuilder content = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null){
                content.append(line).append("\n\n");
            }
        }
        catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }
        return content.toString();
    }

}
