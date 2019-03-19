import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

public class CustomerSpyTest {

    @Spy
    private CustomerDao daoSpy;

    @InjectMocks
    private CustomerService service;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test01() {
        Customer customer = new Customer();
        customer.setPhone("130xxxx");
        assertThat(service.addCustomer(customer), is(true));

        verify(daoSpy).save(any(Customer.class));
        verify(daoSpy, times(1)).exists(anyString());
        verify(daoSpy, never()).delete(any(Customer.class));
    }
}
