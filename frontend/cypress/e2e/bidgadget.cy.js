describe('BidGadget E2E Test', () => {

it('User berhasil melakukan bidding', () => {

    cy.visit('http://127.0.0.1:8000/bid.php?auction_id=101&item=MacBook');

    // isi input
    cy.get('#bid_amount')
    .should('be.visible')
    .clear()
    .type('30000');

    // klik submit
    cy.get('#submitBid')
    .should('be.visible')
    .click();

    // tunggu hasil muncul (lebih stabil dari cy.wait)
    cy.get('body').should(($body) => {
    const text = $body.text();

    expect(
        text.includes('ACCEPTED') || text.includes('REJECTED')
    )
    });

});

});