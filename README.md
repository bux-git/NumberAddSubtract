# NumberAddSubtract
加减控制，可以设置最大值最小值，档数字改变 达到最大值或者最小值时回调

     mAddSubtractView.setLimit(500, 200000, 30)
                    .setListener(new AddSubtractView.OnAddSubtractListener() {


                        @Override
                        public void onMoreMax() {
                            Log.d(TAG, "onMoreMax: ");
                            Toast.makeText(MainActivity.this, "More than the max number!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onLessMin() {
                            Log.d(TAG, "onLessMin: ");
                            Toast.makeText(MainActivity.this, "Less than the min number!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onNumberChange(int number) {
                            Log.d(TAG, "onNumberChange: " + number);
                            Toast.makeText(MainActivity.this, "Number changed number:" + number, Toast.LENGTH_SHORT).show();
                        }


                    });

![image](https://github.com/bux-git/NumberAddSubtract/raw/master/number_change.png)