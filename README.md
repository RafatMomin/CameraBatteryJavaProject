## COM S 227: Project 1
The purpose of this assignment is to give you some practice with the process of implementing a
class from a specification and testing whether your implementation conforms to the
specification. You'll also get practice using variables and modular arithmetic.
For this assignment you will implement one class, called CameraBattery, that models a
removable and rechargeable camera battery. The battery can be charged both directly by the
camera when connect to a USB port and by an external "wall wart" battery charger. However,
the battery can only be in one place at a time, either connected to the camera, connected to the
external charger, or disconnect for any device. The battery has a maximum capacity to which it
can be charged. The rate of charge when connect to the camera is fixed, while the rate of charge
when connected to the external charger is determined by the charger setting. The charger setting
is a number between 0 inclusive and NUM_CHARGER_SETTINGS exclusive. The charger
setting is set by the user repeatedly pressing a single settings button. Each press the setting
increases by one. However, when the maximum setting is reached the next button press puts it
back to setting 0.