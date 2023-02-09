package ie.tudublin;

import processing.core.PApplet;

public class Loops extends PApplet {
	// Constants
	final RGB RED = new RGB(255, 0, 0);
	final RGB CYAN = new RGB(0, 255, 255);
	final RGB BLACK = new RGB(0, 0, 0);

	// HoverBoxes
	HoverBox[] hoverBoxes = new HoverBox[7];

	int mode = 0;
	int submode = 0;

	public void settings() {
		fullScreen();
		size(1000, 1000);
	}

	public void setup() {
		// Mode 1.1
		this.hoverBoxes[0] = new HoverBox(0, 0, width/2, height, BLACK, CYAN);
		this.hoverBoxes[1] = new HoverBox(width/2, 0, width/2, height, BLACK, CYAN);

		// Mode 1.2
		this.hoverBoxes[2] = new HoverBox(0, 0, width/2, height/2, BLACK, CYAN);
		this.hoverBoxes[3] = new HoverBox(0, height/2, width/2, height/2, BLACK, CYAN);
		this.hoverBoxes[4] = new HoverBox(width/2, 0, width/2, height/2, BLACK, CYAN);
		this.hoverBoxes[5] = new HoverBox(width/2, height/2, width/2, height/2, BLACK, CYAN);

		// Mode 1.3
		this.hoverBoxes[6] = new HoverBox(width/3, 2*height/5, width/3, height/5, CYAN, RED);
	}


	public void keyPressed() {
		mode = key - '0';
		println(mode);
	}

	public void mouseClicked() {
		this.submode = (this.submode + 1) % 3;
		println(submode);
	}


	public void draw() {
		background(0);
		fill(255);

		switch (mode) {
			case 0: {
				switch (submode) {
					case 0: {
						this.hoverBoxes[0].render();
						this.hoverBoxes[1].render();
						break;
					}
					case 1: {
						this.hoverBoxes[2].render();
						this.hoverBoxes[3].render();
						this.hoverBoxes[4].render();
						this.hoverBoxes[5].render();
						break;
					}
					case 2: {
						this.hoverBoxes[6].render();
						break;
					}
				}
				break;
			}
			case 1: {
				// Square cross
				for (int i = 0; i < 10; i++) {
					fill(255/10*i);
					square(width/10, height/10, width/10);
					square(width-width/10, height/10, width/10);
				}
				break;
			}
			case 2: {
				break;
			}
			case 3: {
				break;
			}
			case 4: {
				break;
			}
			case 5: {
				break;
			}
			case 6: {
				break;
			}
			case 7: {
				break;
			}
			case 8: {
				break;
			}
			case 9: {
				break;
			}
		}


	}

	class HoverBox {
		float x;
		float y;
		float w;
		float h;
		RGB color;
		RGB hoverColor;

		HoverBox(float x, float y, float w, float h, RGB color, RGB hoverColor) {
			this.x = x;
			this.y = y;
			this.w = w;
			this.h = h;
			this.color = color;
			this.hoverColor = hoverColor;
		}

		void render() {
			if (isHovering()) {
				fill(this.hoverColor.r, this.hoverColor.g, this.hoverColor.b);
			} else {
				fill(this.color.r, this.color.g, this.color.b);
			}
			rect(x, y, w, h);
		}

		boolean isHovering() {
			return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
		}
	}

	class RGB {
		public int r;
		public int g;
		public int b;

		RGB(int r, int g, int b) {
			this.r = r;
			this.g = g;
			this.b = b;
		}
	}
}
