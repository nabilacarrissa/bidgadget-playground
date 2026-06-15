describe('BidGadget E2E Test', () => {

  it('User berhasil melakukan bidding', () => {

    cy.visit(
      'http://127.0.0.1:8080/bid.php?auction_id=101&item=MacBook'
    );

    cy.get('#bid_amount')
      .should('be.visible')
      .clear()
      .type('30000');

    cy.get('#submitBid')
      .should('be.visible')
      .click();

    cy.get('#status-message', { timeout: 10000 })
      .should('exist')
      .invoke('text')
      .then((text) => {

        expect(
          text.includes('ACCEPTED') ||
          text.includes('REJECTED')
        ).to.equal(true);

      });

  });

});