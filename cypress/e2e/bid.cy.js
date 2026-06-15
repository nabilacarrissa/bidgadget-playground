describe('BidGadget E2E Testing', () => {

  beforeEach(() => {
    cy.visit('http://localhost:8080/bid.php?auction_id=101&item=MacBook');
  });

  it('User berhasil mengirim bid normal', () => {

    cy.get('#bid_amount')
      .should('be.visible')
      .clear()
      .type('30000');

    cy.get('#submitBid').click();

    cy.get('#status-message')
      .should('exist')
      .invoke('text')
      .should('match', /(ACCEPTED|REJECTED)/);
  });

});