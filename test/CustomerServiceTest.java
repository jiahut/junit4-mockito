import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.mockito.stubbing.Answer;


public class CustomerServiceTest {

    @Mock
    private CustomerDao daoMock;

    @InjectMocks
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgument;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddCustomer_returnNewCustomer() {
        when(daoMock.save(any(Customer.class))).thenReturn(true);
        Customer customer = new Customer();
        assertThat(customerService.addCustomer(customer), is(notNullValue()));
    }

    @Test
    public void testAddCustomer_returnNewCustomerWithPhone() {
        when(daoMock.save(any(Customer.class))).thenAnswer(
                (Answer<Boolean>) invocation -> {
                    Object[] arguments = invocation.getArguments();
                    if (arguments != null && arguments.length > 0 && arguments[0] != null) {
                        Customer customer = (Customer) arguments[0];
                        customer.setPhone("130xxxxx");
                        return true;
                    }
                    return false;
                }
        );

        Customer customer = new Customer();
        assertThat(customerService.addCustomer(customer), is(notNullValue()));

    }

    @Test(expected = RuntimeException.class)
    public void testAddCustomer_thrownException() {

        when(daoMock.save(any(Customer.class))).thenThrow(RuntimeException.class);

        Customer customer = new Customer();

        customerService.addCustomer(customer);
    }


    @Test
    public void testDaoUpdateCustomer_side_effect() {
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            if (args != null && args.length > 1 && args[0] != null && args[1] != null) {
                Customer customer = (Customer) args[0];
                String email = (String) args[1];
                customer.setEmail(email);
            }
            return null;
        }).when(daoMock).updateEmail(any(Customer.class), any(String.class));

        Customer customer = customerService.changeEmail("old@t.com", "new@t.com");
        assertThat(customer, is(notNullValue()));
        assertThat(customer.getEmail(), is(equalTo("new@t.com")));
    }


    @Test(expected = RuntimeException.class)
    public void testDaoUpdateCustomer_throwsException() {
        doThrow(RuntimeException.class).when(daoMock).updateEmail(any(Customer.class)
                , any(String.class));

        customerService.changeEmail("old@t.com", "new@t.com");
    }


    @Test
    public void test01() {
        when(daoMock.save(any(Customer.class))).thenReturn(true);
        Customer customer = new Customer();
        customer.setPhone("130xxxx");
        assertThat(customerService.addCustomer(customer), is(true));

        verify(daoMock).save(any(Customer.class));

        verify(daoMock, times(1)).exists(anyString());

        verify(daoMock, never()).delete(any(Customer.class));

    }


    @Test
    public void test_capture() {
        Customer customer = new Customer();
        customer.setEmail("test@t.com");
        customerService.addCustomer(customer);
        verify(daoMock).save(customerArgument.capture());
        assertThat(customerArgument.getValue().getEmail(), is(notNullValue()));
    }
}


