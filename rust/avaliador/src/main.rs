
mod tokens;

use tokens::generate_tokens;
fn main() {
    generate_tokens( "(1 + 2 + 3) * 43");
}
