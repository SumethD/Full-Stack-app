import React from 'react';
import { render, fireEvent, waitFor } from '@testing-library/react';
import { describe, test, expect } from 'vitest'
import Login from "../pages/login/login";


// Describe block to group related tests
describe('Login', () => {
  it('logs in a user with valid credentials', async () => {
    // Arrange: Render the login component
    const { getByLabelText, getByText } = render(<Login />);

    // Act: Simulate user input and form submission
    const emailInput = getByLabelText('Email:');
    const passwordInput = getByLabelText('Password:');
    const submitButton = getByText('Login');

    //fires evebt and simulates click of button
    
    fireEvent.change(emailInput, { target: { value: 'test@test1.com' } });
    fireEvent.change(passwordInput, { target: { value: 'testpassword' } });
    fireEvent.click(submitButton);

    // Assert: Check if the user is logged in
    await waitFor(() => {
      const loggedInMessage = getByText('Logged in successfully');
      expect(loggedInMessage).toBe('Logged in successfully');
    });
  });

  it('shows an error message with invalid credentials', async () => {
    // Arrange: Render the login component
    const { getByLabelText, getByText } = render(<Login />);

    // Act: Simulate user input and form submission with invalid credentials
    const emailInput = getByLabelText('Email:');
    const passwordInput = getByLabelText('Password:');
    const submitButton = getByText('Login');
    

    //fires event and simulates click of button
    fireEvent.change(emailInput, { target: { value: 'invalid@test1.com' } });
    fireEvent.change(passwordInput, { target: { value: 'invalidpassword' } });
    fireEvent.click(submitButton);

    // Assert: Check if the error message is displayed
    await waitFor(() => {
      const errorMessage = getByText('Invalid credentials');
      expect(errorMessage).toBe('Invalid credentials');
    });
  });
});