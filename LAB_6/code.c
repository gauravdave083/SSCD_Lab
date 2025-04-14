#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

char *input;     // Pointer to current character
char current;    // Lookahead character

void nextChar() {
    current = *input++;
}

void error() {
    printf("Invalid expression!\n");
    exit(1);
}

// Function declarations
void E(); void E_();
void T(); void T_();
void F();

// Expression: E → T E'
void E() {
    T();
    E_();
}

// Expression Prime: E' → + T E' | ε
void E_() {
    while (current == '+') {
        nextChar();
        T();
        printf(" +");
    }
}

// Term: T → F T'
void T() {
    F();
    T_();
}

// Term Prime: T' → * F T' | ε
void T_() {
    while (current == '*') {
        nextChar();
        F();
        printf(" *");
    }
}

// Factor: F → (E) | variable (like a, b, x, y, etc.)
void F() {
    if (current == '(') {
        nextChar();
        E();
        if (current == ')') {
            nextChar();
        } else {
            error();
        }
    } else if (isalnum(current)) {
        printf(" %c", current);
        nextChar();
    } else {
        error();
    }
}

int main() {
    char expr[100];
    printf("Enter an arithmetic expression: ");
    scanf("%s", expr);

    input = expr;
    nextChar();  // Initialize the first character
    E();         // Start parsing from the start symbol E

    if (current == '\0') {
        printf("\nParsing successful!\n");
    } else {
        error();
    }

    return 0;
}
