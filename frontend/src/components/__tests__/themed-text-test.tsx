import { render, screen } from '@testing-library/react-native';

import { ThemedText } from '@/components/themed-text';

describe('ThemedText', () => {
  it('renders its children', async () => {
    await render(<ThemedText>Hello MyPlunt</ThemedText>);

    expect(screen.getByText('Hello MyPlunt')).toBeOnTheScreen();
  });
});
