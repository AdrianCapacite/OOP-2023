package ie.tudublin;

import org.w3c.dom.css.RGBColor;

import processing.core.PApplet;

public class Conditionals extends PApplet {
    RGB black = new RGB(0, 0, 0);
    RGB cyan = new RGB(0, 255, 255);
    RGB red = new RGB(255, 0, 0);

	int mode = 0;

	public void settings() {
		size(1000, 1000);
	}

	public void setup() {
	}



	public void keyPressed() {

		mode = key - '0';
		println(mode);
	}

	public void draw() {
		background(0);

        switch (mode) {
            case 0:
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

        boolean isHovering() {
            return mouseX > x && mouseX < x + w && mouseY > y && mouseY < y + h;
        }

        void render() {
            if (isHovering()) {
                fill(hoverColor.r, hoverColor.g, hoverColor.b);
            } else {
                fill(255, 0, 0);
            }
            rect(x, y, w, h);
        }
    }

    class RGB {
        public float r;
        public float g;
        public float b;

        RGB(float r, float g, float b) {
            this.r = r;
            this.g = g;
            this.b = b;
        }
    }
}
