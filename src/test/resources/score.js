var hand = new Hand();
var cursedHoardItems = false;
var cursedHoardSuits = false;

function clear() {
    hand = new Hand();
}

function add(card) {
    hand.addCard(base[card]);
}

function getScore() {
    let score = hand.score(new Discard());
    return score;
}

function getCardById(id) {
    return hand.getCardById(id);
}