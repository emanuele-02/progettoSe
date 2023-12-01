import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RuleFileManager {
    private String ruleFilePath;

    public RuleFileManager(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
    }

    public void saveRulesToFile(List<Rule> ruleList) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ruleFilePath))) {
            out.writeObject(ruleList);
            System.out.println("Rules successfully saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Rule> loadRulesFromFile() {
        List<Rule> loadedRules = new ArrayList<>();
      
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ruleFilePath))) {
            loadedRules = (List<Rule>) in.readObject();
            System.out.println("Rules successfully loaded.");
        } catch (FileNotFoundException e) {
            // Il file non esiste, crea una nuova lista vuota
            System.out.println("File non trovato. Creazione di un nuovo file.");
            saveRulesToFile(loadedRules);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedRules;
    }

    public String getRuleFilePath() {
        return ruleFilePath;
    }

    public void setRuleFilePath(String ruleFilePath) {
        this.ruleFilePath = ruleFilePath;
    }
}