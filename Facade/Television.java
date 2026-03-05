package Facade;

class Television {
    public void turnOn()  { System.out.println("TV: Turning ON");  }
    public void turnOff() { System.out.println("TV: Turning OFF"); }
}

class SoundSystem {
    public void turnOn()      { System.out.println("Sound System: Turning ON");     }
    public void setVolume(int level) { System.out.println("Sound System: Volume set to " + level); }
    public void turnOff()     { System.out.println("Sound System: Turning OFF");    }
}

class DVDPlayer {
    public void turnOn()       { System.out.println("DVD Player: Turning ON");      }
    public void play(String movie) { System.out.println("DVD Player: Playing '" + movie + "'"); }
    public void stop()         { System.out.println("DVD Player: Stopping");        }
    public void turnOff()      { System.out.println("DVD Player: Turning OFF");     }
}

class Lights {
    public void dim(int level) { System.out.println("Lights: Dimming to " + level + "%"); }
    public void brighten()     { System.out.println("Lights: Brightening to 100%");       }
}