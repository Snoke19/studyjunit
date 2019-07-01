import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class AssertTest {

  class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message) {
      super(message);
    }

    private static final long serialVersionUID = 1L;
  }

  class Account {
    int balance;
    String name;

    Account(String name) {
      this.name = name;
    }

    void deposit(int dollars) {
      balance += dollars;
    }

    void withdraw(int dollars) {
      if (balance < dollars) {
        throw new InsufficientFundsException("balance only " + balance);
      }
      balance -= dollars;
    }

    public String getName() {
      return name;
    }

    public int getBalance() {
      return balance;
    }

    public boolean hasPositiveBalance() {
      return balance > 0;
    }
  }

  class Customer {
    List<Account> accounts = new ArrayList<>();

    void add(Account account) {
      accounts.add(account);
    }

    Iterator<Account> getAccounts() {
      return accounts.iterator();
    }
  }

  private Account account;


  @Rule
  public ExpectedException thrown = ExpectedException.none();


  @Before
  public void createAccount() {
    account = new Account("xyz");
  }


  @Test
  public void hasPositiveBalance() {
    account.deposit(50);
    assertTrue(account.hasPositiveBalance());
  }


  @Test
  public void depositIncreasesBalance() {
    int initialBalance = account.getBalance();
    account.deposit(100);

    assertTrue(account.getBalance() > initialBalance);
    assertThat(account.getBalance() > 0, is(true));
    assertThat(account.getBalance(), equalTo(100));
  }


  @Test
  public void compareArrays() {
    assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b", "c"}));
    assertThat(Arrays.asList("a", "d"), equalTo(Arrays.asList("a", "d")));
  }


  @Test
  public void compareAccountName() {
    Account account = new Account("x");
    assertThat(account.getName(), startsWith("x"));
  }


  @Test
  public void compareAccountNameEqualName() {
    Account account = new Account("xs");
    assertThat(account.getName(), not(equalTo("x")));
  }


  @Test
  public void nameNotNull() {
    assertThat(account.getName(), is(not(nullValue())));
    assertThat(account.getName(), is(notNullValue()));
  }


  @Test
  public void nameNotNullString() {
    assertThat(account.getName(), is(notNullValue()));
    assertThat(account.getName(), equalTo("xyz"));
  }


  @Test
  public void testWithWorthlessAssertionComment() {
    account.deposit(50);
    assertThat("account balance is 100", account.getBalance(), equalTo(50));
  }


  @Test(expected=InsufficientFundsException.class)
  public void throwsWhenWithdrawingTooMuch() {
    account.withdraw(1);
  }


  @Test
  public void exceptionRule() {
    thrown.expect(InsufficientFundsException.class);
    thrown.expectMessage("balance only 0");

    account.withdraw(100);
  }
}
