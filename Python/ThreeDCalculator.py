import pygame
import math
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.backends.backend_agg import FigureCanvasAgg
import io

class Simple3DCalculator:
    def __init__(self):
        pygame.init()
        self.width, self.height = 900, 600
        self.screen = pygame.display.set_mode((self.width, self.height))
        pygame.display.set_caption("3D Calculator with Matplotlib")
        
        # Calculator state
        self.display_text = "0"
        self.current_input = ""
        self.result = 0
        self.last_operator = ""
        self.start_new_input = True
        
        # Colors
        self.colors = {
            'background': (40, 44, 52),
            'display_bg': (30, 33, 40),
            'display_text': (152, 195, 121),
            'button_number': (86, 182, 194),
            'button_operator': (255, 198, 109),
            'button_function': (198, 120, 221),
            'button_clear': (224, 108, 117),
            'button_equals': (120, 220, 130)
        }
        
        self.buttons = self.create_buttons()
        self.fig = None
        self.update_3d_plot()
    
    def create_buttons(self):
        buttons = []
        button_layout = [
            ['7', '8', '9', '/', 'sin'],
            ['4', '5', '6', '*', 'cos'],
            ['1', '2', '3', '-', '√'],
            ['0', '.', '=', '+', 'C']
        ]
        
        button_width = 70
        button_height = 50
        start_x = 500
        start_y = 200
        spacing = 10
        
        for row_idx, row in enumerate(button_layout):
            for col_idx, label in enumerate(row):
                x = start_x + col_idx * (button_width + spacing)
                y = start_y + row_idx * (button_height + spacing)
                
                if label in '0123456789':
                    color = self.colors['button_number']
                elif label in '+-*/':
                    color = self.colors['button_operator']
                elif label == '=':
                    color = self.colors['button_equals']
                elif label == 'C':
                    color = self.colors['button_clear']
                else:
                    color = self.colors['button_function']
                
                buttons.append({
                    'rect': pygame.Rect(x, y, button_width, button_height),
                    'label': label,
                    'color': color
                })
        
        return buttons
    
    def update_3d_plot(self):
        # Create a 3D plot using matplotlib
        from mpl_toolkits.mplot3d import Axes3D
        
        self.fig = plt.figure(figsize=(4, 3), dpi=100)
        ax = self.fig.add_subplot(111, projection='3d')
        
        # Create a mathematical surface
        x = np.linspace(-5, 5, 50)
        y = np.linspace(-5, 5, 50)
        X, Y = np.meshgrid(x, y)
        
        try:
            # Use current input for function if it's a valid number
            if self.current_input:
                freq = float(self.current_input) / 10.0
            else:
                freq = 1.0
        except:
            freq = 1.0
            
        Z = np.sin(np.sqrt(X**2 + Y**2) * freq)
        
        # Plot the surface
        surf = ax.plot_surface(X, Y, Z, cmap='viridis', alpha=0.8)
        ax.set_xlabel('X')
        ax.set_ylabel('Y')
        ax.set_zlabel('Z')
        ax.set_title('3D Function Visualization')
        
        # Convert matplotlib figure to pygame surface
        canvas = FigureCanvasAgg(self.fig)
        buf = io.BytesIO()
        canvas.print_raw(buf)
        buf.seek(0)
        plot_image = pygame.image.frombuffer(buf.getvalue(), canvas.get_renderer().width, 
                                           canvas.get_renderer().height, 'RGBA')
        buf.close()
        plt.close(self.fig)
        
        return plot_image
    
    def draw(self):
        self.screen.fill(self.colors['background'])
        
        # Draw 3D plot
        plot_surface = self.update_3d_plot()
        self.screen.blit(plot_surface, (50, 50))
        
        # Draw display
        pygame.draw.rect(self.screen, self.colors['display_bg'], (450, 50, 400, 80))
        font = pygame.font.Font(None, 48)
        text_surface = font.render(self.display_text, True, self.colors['display_text'])
        self.screen.blit(text_surface, (460, 70))
        
        # Draw buttons
        for button in self.buttons:
            pygame.draw.rect(self.screen, button['color'], button['rect'])
            pygame.draw.rect(self.screen, (255, 255, 255), button['rect'], 2)
            
            button_font = pygame.font.Font(None, 28)
            text_surface = button_font.render(button['label'], True, (255, 255, 255))
            text_rect = text_surface.get_rect(center=button['rect'].center)
            self.screen.blit(text_surface, text_rect)
    
    def handle_button_click(self, button_label):
        if button_label in '0123456789':
            self.handle_number_input(button_label)
        elif button_label == '.':
            self.handle_decimal_point()
        elif button_label in '+-*/':
            self.handle_operator(button_label)
        elif button_label == '=':
            self.calculate_result()
        elif button_label == 'C':
            self.clear_all()
        elif button_label in ['sin', 'cos']:
            self.handle_trig_function(button_label)
        elif button_label == '√':
            self.handle_square_root()
    
    def handle_number_input(self, number):
        if self.start_new_input:
            self.current_input = number
            self.start_new_input = False
        else:
            self.current_input += number
        self.display_text = self.current_input
    
    def handle_decimal_point(self):
        if self.start_new_input:
            self.current_input = "0."
            self.start_new_input = False
        elif '.' not in self.current_input:
            self.current_input += "."
        self.display_text = self.current_input
    
    def handle_operator(self, operator):
        if self.current_input:
            self.calculate_result()
        self.last_operator = operator
        self.start_new_input = True
    
    def calculate_result(self):
        if not self.current_input:
            return
        
        try:
            input_value = float(self.current_input)
            
            if self.last_operator:
                if self.last_operator == '+':
                    self.result += input_value
                elif self.last_operator == '-':
                    self.result -= input_value
                elif self.last_operator == '*':
                    self.result *= input_value
                elif self.last_operator == '/':
                    if input_value != 0:
                        self.result /= input_value
                    else:
                        self.display_text = "Error"
                        return
            else:
                self.result = input_value
            
            self.current_input = str(self.result)
            self.display_text = self.current_input
            self.last_operator = ""
            self.start_new_input = True
            
        except ValueError:
            self.display_text = "Error"
    
    def handle_trig_function(self, function):
        if not self.current_input:
            return
        
        try:
            value = math.radians(float(self.current_input))
            if function == 'sin':
                result = math.sin(value)
            elif function == 'cos':
                result = math.cos(value)
            
            self.current_input = str(result)
            self.display_text = self.current_input
            self.start_new_input = True
            
        except ValueError:
            self.display_text = "Error"
    
    def handle_square_root(self):
        if not self.current_input:
            return
        
        try:
            value = float(self.current_input)
            if value >= 0:
                self.current_input = str(math.sqrt(value))
                self.display_text = self.current_input
                self.start_new_input = True
            else:
                self.display_text = "Error"
                
        except ValueError:
            self.display_text = "Error"
    
    def clear_all(self):
        self.current_input = ""
        self.result = 0
        self.last_operator = ""
        self.display_text = "0"
        self.start_new_input = True
    
    def run(self):
        clock = pygame.time.Clock()
        running = True
        
        while running:
            for event in pygame.event.get():
                if event.type == pygame.QUIT:
                    running = False
                elif event.type == pygame.MOUSEBUTTONDOWN:
                    for button in self.buttons:
                        if button['rect'].collidepoint(event.pos):
                            self.handle_button_click(button['label'])
                            break
            
            self.draw()
            pygame.display.flip()
            clock.tick(30)
        
        pygame.quit()

if __name__ == "__main__":
    calculator = Simple3DCalculator()
    calculator.run()
