import org.junit.*;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class MockFinalTest {

  @Test
  public void testMockFinal() {
    
    FinalClass c1 = mock(FinalClass.class);
    when(c1.m1()).thenReturn(-1);

    assertThat(c1.m1(), equalTo(-1));
  }
}


