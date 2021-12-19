package screenplay.abilities;

import com.google.gson.Gson;
import net.serenitybdd.screenplay.Ability;
import screenplay.models.Contact;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class CreateContact extends Contact implements Ability{
    private List<Contact> Contacts;
    private Contact ContactInUsed;

    public static CreateContact withData() throws Exception {
        return new CreateContact();
    }

    public CreateContact() throws Exception {
        Gson gson = new Gson();

        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String dataPath  = classLoader.getResource("data/createcontact.json").getPath();

            Reader reader = Files.newBufferedReader(Paths.get(dataPath));
            this.Contacts = Arrays.asList(gson.fromJson(reader, Contact[].class));

            if(this.Contacts.size()==0)
            {
                throw new Exception("Don't have any contact information used to create");
            }
            else
            {
                this.ContactInUsed = this.Contacts.get(0);
            }

        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    public Contact takeAContact()
    {
        if(this.Contacts.size()>0)
        {
            this.ContactInUsed = this.Contacts.get(0);
            return this.ContactInUsed;
        }
        else
        {
            return null;
        }
    }
}
