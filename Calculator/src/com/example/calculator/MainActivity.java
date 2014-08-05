package com.example.calculator;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	static String math = "";
	static double firstNum, secondNum, result;
	static int operator;
	static char opChar;
	static MainActivity main;
	final int ADD = 0, SUBTRACT = 1, MULTIPLY = 2, DIVIDE = 3, POW = 4;
	static final int NOTHING = 0, NUM1 = 1, OPERATOR = 2, NUM2 = 3, RESULT = 4;
	static int state;
	static TextView text;
	static int dPlace;
	static boolean negative;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		main = this;
		createButtons();
		text = (TextView) findViewById(R.id.math);
		text.setText(math);
	}

	private void createButtons() {
		Button num1 = (Button) findViewById(R.id.num1),
		num2 = (Button) findViewById(R.id.num2),
		num3 = (Button) findViewById(R.id.num3),
		num4 = (Button) findViewById(R.id.num4),
		num5 = (Button) findViewById(R.id.num5),
		num6 = (Button) findViewById(R.id.num6), 
		num7 = (Button) findViewById(R.id.num7),
		num8 = (Button) findViewById(R.id.num8),
		num9 = (Button) findViewById(R.id.num9),
		num0 = (Button) findViewById(R.id.num0),
		add = (Button) findViewById(R.id.add),
		subtract = (Button) findViewById(R.id.subtract), 
		multiply = (Button) findViewById(R.id.multiply), 
		divide = (Button) findViewById(R.id.divide),
		pow = (Button) findViewById(R.id.pow), 
		decimal = (Button) findViewById(R.id.decimal),
		clear = (Button) findViewById(R.id.clear), 
		equal = (Button) findViewById(R.id.equal);
		ImageButton post = (ImageButton) findViewById(R.id.post);
		num1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(1);
			}
		});
		num2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(2);
			}
		});
		num3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(3);
			}
		});
		num4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(4);
			}
		});
		num5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(5);
			}
		});
		num6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(6);
			}
		});
		num7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(7);
			}
		});
		num8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(8);
			}
		});
		num9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(9);
			}
		});
		num0.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				addNum(0);
			}
		});
		add.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				operator(ADD);
			}
		});
		subtract.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				operator(SUBTRACT);
			}
		});
		multiply.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				operator(MULTIPLY);
			}
		});
		divide.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				operator(DIVIDE);
			}
		});
		pow.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				operator(POW);
			}
		});
		decimal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (dPlace == 0)
					dPlace = 1;
			}
		});
		clear.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dPlace = 0;
				negative = false;
				firstNum = secondNum = result = 0;
				state = NOTHING;
				math = makeString();
			}
		});
		equal.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (state == NUM2) {
					if (negative) {
						negative = false;
						secondNum *= -1;
					}
					dPlace = 0;
					result = 0;
					if (operator == ADD)
						result = firstNum + secondNum;
					else if (operator == SUBTRACT)
						result = firstNum - secondNum;
					else if (operator == MULTIPLY)
						result = firstNum * secondNum;
					else if (operator == DIVIDE)
						result = firstNum / secondNum;
					else if (operator == POW)
						result = Math.pow(firstNum, secondNum);
					state = RESULT;
				}
				math = makeString();
			}
		});
		post.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (state == NUM2) {
					if (negative) {
						negative = false;
						secondNum *= -1;
					}
					dPlace = 0;
					if (operator == ADD)
						result = firstNum + secondNum;
					else if (operator == SUBTRACT)
						result = firstNum - secondNum;
					else if (operator == MULTIPLY)
						result = firstNum * secondNum;
					else if (operator == DIVIDE)
						result = firstNum / secondNum;
					else if (operator == POW)
						result = Math.pow(firstNum, secondNum);
					math = makeString();
					MainActivity.post();
					state = RESULT;
				} else if (state == RESULT) {
					MainActivity.post();
				}
			}
		});
	}

	protected void addNum(int num) {
		if (state < RESULT) {
			if (state == OPERATOR)
				state = NUM2;
			if (state == NOTHING || state == NUM1) {
				state = NUM1;
				if (dPlace == 0) {
					firstNum *= 10;
					firstNum += num;
				} else {
					double a = (num / (Math.pow(10, dPlace)));
					for (int i = 0; i < 100; i++)
						System.out.println((num / (Math.pow(10, dPlace))));
					System.out.println(a + " " + (firstNum + a));
					firstNum = firstNum + a;
					dPlace++;
				}
			} else {
				if (dPlace == 0) {
					secondNum *= 10;
					secondNum += num;
				} else {
					float a = (float) (num / (Math.pow(10, dPlace)));
					secondNum += a;
					dPlace++;
				}
			}
		} else {
			state = NUM1;
			firstNum = secondNum = 0;
			firstNum *= 10;
			firstNum += num;
		}
		math = makeString();
	}

	protected void operator(int operator) {
		if (state == NOTHING && operator == SUBTRACT)
			negative = !negative;
		if (state == RESULT) {
			if (operator != SUBTRACT) {
				state = NUM1;
				firstNum = result;
				secondNum = result = 0;
			} else {
				negative = !negative;
				firstNum = secondNum = 0;
				state = NOTHING;
				dPlace = 0;
			}
		}
		if (state == NUM1) {
			if (negative) {
				negative = false;
				firstNum *= -1;
			}
			dPlace = 0;
			state = OPERATOR;
			MainActivity.operator = operator;
			if (operator == ADD)
				opChar = '+';
			else if (operator == SUBTRACT)
				opChar = '-';
			else if (operator == MULTIPLY)
				opChar = 'X';
			else if (operator == DIVIDE)
				opChar = '/';
			else if (operator == POW)
				opChar = '^';
		} else if (state == OPERATOR || state == NUM2) {
			negative = !negative;
		}
		math = makeString();
	}

	protected static void post() {
		Omlet.postAndGo(math, main);
		state = NOTHING;
		negative = false;
		math = makeString();
		firstNum = 0;
		secondNum = 0;
		operator = 0;
	}

	private static String makeString() {
		String s = "";
		if (state <= NUM1) {
			if (!negative)
				s = firstNum + "";
			else
				s = "-" + firstNum;
		} else if (state <= NUM2) {
			if (!negative)
				s = firstNum + "" + opChar + "" + secondNum;
			else
				s = firstNum + "" + opChar + "-" + secondNum;
		} else {
			s = firstNum + "" + opChar + "" + secondNum + "=" + result;
		}

		if (state <= NUM1)
			text.setTextColor(Color.rgb(0, 150, 0));
		else if (state == OPERATOR)
			text.setTextColor(Color.rgb(150, 0, 0));
		else if (state == NUM2)
			text.setTextColor(Color.rgb(0, 0, 150));
		else if (state == RESULT)
			text.setTextColor(Color.rgb(150, 0, 150));
		text.setText(math);
		return s;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}