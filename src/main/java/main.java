import core.Customer.domain.Customer;
import core.Customer.domain.valueObjects.*;
import io.javalin.Javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class main {
    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7070);
        app.get("/", ctx -> ctx.result("Hello World"));

        app.get("/customers", ctx -> ctx.json(testGet()));

        app.post("/customer", ctx -> {
            CustomerDTO customerDTO = ctx.bodyAsClass(CustomerDTO.class);

            System.out.println(customerDTO.firstName);
            System.out.println(customerDTO.uid);
            ctx.status(201);
        });
    }



    public static List<Customer> testGet() {
        List<Customer> customers = new ArrayList<>();
        customers.add(widthId(new CustomerId(UUID.randomUUID())));
        customers.add(widthId(new CustomerId(UUID.randomUUID())));
        customers.add(widthId(new CustomerId(UUID.randomUUID())));

        return customers;

    }

    public static Customer widthId(CustomerId id) {
        return new Customer(id,
                anyFirstName(),
                anyLastName(),
                anyPersonnumber(),
                anyMobilePhoneNumber());
    }


    private static FirstName anyFirstName() {
        return new FirstName("ANY_FIRST_NAME");
    }

    private static LastName anyLastName() {
        return new LastName("ANY_LAST_NAME");
    }

    private static Personnumber anyPersonnumber() {
        return new Personnumber("ANY_PERSONNUMBER");
    }

    private static MobilePhoneNumber anyMobilePhoneNumber() {
        return new MobilePhoneNumber("ANY_PHONE_NUMBER");
    }
}
