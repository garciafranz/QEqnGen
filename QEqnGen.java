/* SPDX-License-Identifier: GPL-2.0-only */

import java.security.SecureRandom;

public class QEqnGen {
  private static final int LO = -99;
  private static final int HI = 99;

  private static SecureRandom rng = new SecureRandom();

  public static int boundedInt(int lo_inclusive, int hi_inclusive) {
    if (lo_inclusive == hi_inclusive) {
      return lo_inclusive;
    }
    if (lo_inclusive > hi_inclusive) {
      int t = lo_inclusive;
      lo_inclusive = hi_inclusive;
      hi_inclusive = t;
    }
    return lo_inclusive + rng.nextInt(hi_inclusive - lo_inclusive + 1);
  }

  public static int boundedInt() {
    return boundedInt(LO, HI);
  }

  public static int abs(int n) {
    return (0 > n) ? n * -1 : n;
  }

  /** Returns a quadratic equation and its solution similar to the form (4, -2, 5) 4xx + 12x + 40 */
  public static String getRandomQuadratic() {
    int a, b, c, r, s;
    a = b = c = r = s = 1;
    boolean rampant = true;
    while (rampant) {
      a = boundedInt();
      if (50 < abs(a) || 2 > abs(a)) {
        continue;
      }
      r = boundedInt();
      s = boundedInt();
      c = a * r * s;
      if (HI < abs(c) || 0 == c) {
        continue;
      }
      b = (-1) * r - s;
      b *= a;
      rampant = (0 == b || HI < abs(b));
    }
    return getFormattedSolution(a, r, s) + "  " + getFormattedQuadratic(a, b, c);
  }
  
  public static String getFormattedQuadratic(int a, int b, int c) {
    String eqn = (0 < a) ? "" : "-";
    eqn += ((1 == abs(a)) ? "" : abs(a));
    eqn += "xx " + ((0 < b) ? "+ " : "- ");
    eqn += ((1 == abs(b)) ? "" : abs(b));
    return eqn + "x " + ((0 < c) ? "+ " : "- ") + abs(c);
  }

  public static String getFormattedSolution(int a, int r, int s) {
    return "(" + a + ", " + r + ", " + s + ")";
  }

  public static void main(String[] args) {
    System.out.println(getRandomQuadratic());
  }
}
