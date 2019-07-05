package junit_in_action_test;

import JunitAction.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDefaultController {

  private DefaultController controller;
  private Request request;
  private RequestHandler handler;


  @Before
  public void initialize() {
    controller = new DefaultController();
    request = new SampleRequest();
    handler = new SampleHandler();
    controller.addHandler(request, handler);
  }


  private class SampleRequest implements Request {

    private static final String DEFAULT_NAME = "Test";
    private String name;

    public SampleRequest(String name) {
      this.name = name;
    }

    public SampleRequest() {
      this(DEFAULT_NAME);
    }

    public String getName() {
      return this.name;
    }

    @Override
    public boolean equals(Object obj) {
      return super.equals(obj);
    }

    public int hashCode() {
      return DEFAULT_NAME.hashCode();
    }
  }

  private class SampleHandler implements RequestHandler {
    public Response process(Request request) {
      return new SampleResponse();
    }
  }

  private class SampleResponse implements Response {}

  private class SampleExceptionHandler implements RequestHandler {

    public Response process(Request request) throws Exception {
      throw new Exception("error processing request");
    }
  }


  @Test
  public void testAddHandler() {
    RequestHandler handler2 = controller.getHandler(request);
    assertSame(handler2, handler);
  }


  @Test
  public void testProcessRequest() {
    Request request = new SampleRequest("test");
    RequestHandler handler = new SampleHandler();
    controller.addHandler(request, handler);
    Response response = controller.processRequest(request);
    assertNotNull("Must not return a null response", response);
    assertEquals("Response should be of type SampleResponse", SampleResponse.class, response.getClass());
  }

  @Test
  public void testAddAndProcess() {

    Request request = new SampleRequest("testError");
    RequestHandler handler = new SampleHandler();
    controller.addHandler(request, handler);
    RequestHandler handler2 = controller.getHandler(request);
    assertEquals(handler2,handler);

    Response response = controller.processRequest(request);
    assertNotNull("Must not return a null response", response);
    assertEquals(SampleResponse.class, response.getClass());
  }

  @Test
  public void testProcessRequestAnswersErrorResponse() {
    SampleRequest request = new SampleRequest("testError");
    SampleExceptionHandler handler = new SampleExceptionHandler();
    controller.addHandler(request, handler);
    Response response = controller.processRequest(request);
    assertNotNull("Must not return a null response", response);
    assertEquals(ErrorResponse.class, response.getClass());
  }


  @Test(expected=RuntimeException.class)
  public void testGetHandlerNotDefined() {

    SampleRequest request = new SampleRequest("testNotDefined");
    controller.getHandler(request);
  }


  @Test(expected=RuntimeException.class)
  public void testAddRequestDuplicateName() {

    SampleRequest request = new SampleRequest();
    SampleHandler handler = new SampleHandler();

    controller.addHandler(request, handler);
  }
}
