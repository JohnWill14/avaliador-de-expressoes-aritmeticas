
mod tokens;
mod convert;
mod solve;

use tokens::generate_tokens;
use convert::convert_infix_expression_to_postfix;
use solve::solve_postfix_expression;

use std::io::prelude::*;

fn main() {

    let input = std::io::stdin();
    let mut input = input.lock();

    loop{
        print!(">>> ");
        std::io::stdout().flush().expect("can't flush stdout");
        let mut express = String::new();
        input
            .read_line(&mut express)
            .expect("can't read line from stdin");


        if express.eq("exit"){
            break;
        }

        let mut tokens = generate_tokens( &express.to_owned() );
        let mut parser = convert_infix_expression_to_postfix(&mut tokens);
        let _value = solve_postfix_expression(&mut parser);

    }
}
