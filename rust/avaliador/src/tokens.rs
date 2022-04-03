mod regex; 
use regex::Regex;

    pub fn generateTokens() {
        let mut tokens = Vec::new();
        let re = Regex::new(r"\s?(-?\d+|[+*-/()])\s?").unwrap();
        let text = "3 + 4";
        for cap in re.captures_iter(text) {
            println!("{:?}", &cap);
        }
    }

    

